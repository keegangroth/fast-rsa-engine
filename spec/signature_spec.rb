require_relative 'setup'

describe 'Signature' do

  let( :this ) { File.expand_path( '..', __FILE__) }

  let( :private_key ) { OpenSSL::PKey::RSA.new(File.read("#{this}/foo.pem")) }

  let( :msg ) { "THIS IS A TEST" }

  let( :rounds ) { 10 }

  it 'is faster the regular signature' do
    # clear the fast engines
    engines.clear

    start = Time.new.to_f
    rounds.times do
      private_key.sign(OpenSSL::Digest.new('sha512'), msg)
    end
    delta1 = Time.new.to_f - start

    # setup the fast engines
    engines.clear
    # this creates a warning
    load( "${this}/../lib/fast-rsa-engine.rb" )

    start = Time.new.to_f
    rounds.times do
      private_key.sign(OpenSSL::Digest.new('sha512'), msg)
    end
    delta2 = Time.new.to_f - start

    expect( delta1 ).to be > 2 * delta2
  end
  
end
